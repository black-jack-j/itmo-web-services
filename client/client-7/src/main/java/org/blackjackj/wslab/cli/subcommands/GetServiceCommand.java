package org.blackjackj.wslab.cli.subcommands;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.client.transport.TransportException;
import org.blackjackj.wslab.exception.WslabConfigurationException;
import org.uddi.api_v3.*;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDISecurityPortType;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.rmi.RemoteException;

@Command(name = "get", description = "Get list of products satisfying specified criteria", mixinStandardHelpOptions = true)
public class GetServiceCommand implements Runnable {

    @Option(names = {"-n", "--name"})
    private String name;

    private UDDIInquiryPortType inquiryPortType;
    private UDDISecurityPortType securityPortType;

    @Override
    public void run() {
        try {
            Transport transport = getTransport();
            inquiryPortType = transport.getUDDIInquiryService();
            securityPortType = transport.getUDDISecurityService();
            String token = getToken("wslab", "wslab");
            ServiceList services = findServiceByName(token, name);
            printServiceInfo(services.getServiceInfos());
            securityPortType.discardAuthToken(new DiscardAuthToken(token));
        } catch (WslabConfigurationException | TransportException | RemoteException dispositionReportFaultMessage) {
            dispositionReportFaultMessage.printStackTrace();
        }
    }

    private void printServiceInfo(ServiceInfos serviceInfos) {
        serviceInfos.getServiceInfo().forEach(serviceInfo -> {
            System.out.println("service key: "+serviceInfo.getServiceKey());
            System.out.println("service name: "+serviceInfo.getName().get(0));
        });
    }

    private Transport getTransport() throws WslabConfigurationException {
        try {
            UDDIClient client = new UDDIClient("META-INF/uddi.xml");
            return client.getTransport("default");
        } catch (ConfigurationException e) {
            throw new WslabConfigurationException(e.getLocalizedMessage());
        }
    }

    private String getToken(String username, String password) throws WslabConfigurationException{
        GetAuthToken tokenRequest = new GetAuthToken();
        tokenRequest.setUserID(username);
        tokenRequest.setCred(password);
        try {
            AuthToken token = securityPortType.getAuthToken(tokenRequest);
            return token.getAuthInfo();
        } catch (RemoteException e) {
            throw new WslabConfigurationException(e.getMessage());
        }
    }

    private ServiceList findServiceByName(String authToken, String serviceName) {
        FindService findService = new FindService();
        try {
            findService.setAuthInfo(authToken);
            FindQualifiers qualifiers = new FindQualifiers();
            qualifiers.getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);
            findService.setFindQualifiers(qualifiers);
            Name searchName = new Name();
            searchName.setValue(serviceName);
            findService.getName().add(searchName);
            return inquiryPortType.findService(findService);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return new ServiceList();
    }
}
