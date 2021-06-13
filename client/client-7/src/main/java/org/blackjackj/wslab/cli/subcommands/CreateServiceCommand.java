package org.blackjackj.wslab.cli.subcommands;

import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.blackjackj.wslab.exception.WslabConfigurationException;
import org.uddi.api_v3.*;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "create", description = "Create service and business", mixinStandardHelpOptions = true)
public class CreateServiceCommand implements Runnable {

    @Option(names = {"-b", "--business-name"}, required = true)
    private String businessName;
    @Option(names = {"-s", "--service--name"}, required = true)
    private String serviceName;

    @Override
    public void run() {
        try {
            UDDIClerk clerk = new UDDIClerkProvider().getClerk();
            publishService(clerk);
        } catch (WslabConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void publishService(UDDIClerk clerk) {

        BusinessEntity businessEntity = registerBusiness(clerk, businessName);
        if (businessEntity == null) {
            System.out.println("exception during save");
            throw new IllegalStateException("bad state");
        }
        BusinessService service = registerService(clerk, serviceName, businessEntity.getBusinessKey());
        if (service == null) {
            System.out.println("exception during service save");
            throw new IllegalStateException("bad state");
        }
        clerk.discardAuthToken();
        System.out.println("saved successfully");
    }

    private BusinessEntity registerBusiness(UDDIClerk clerk, String businessName) {
        BusinessEntity entity = new BusinessEntity();
        Name entityName = new Name();
        entityName.setValue(businessName);
        entity.getName().add(entityName);

        return clerk.register(entity);
    }

    private BusinessService registerService(UDDIClerk clerk, String name, String businessKey) {
        BusinessService service = new BusinessService();
        Name serviceName = new Name();
        serviceName.setValue(name);
        service.getName().add(serviceName);

        service.setBusinessKey(businessKey);
        bindService(service, name);
        return clerk.register(service);
    }

    private void bindService(BusinessService service, String location) {
        BindingTemplate myBindingTemplate = new BindingTemplate();
        AccessPoint accessPoint = new AccessPoint();
        accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
        accessPoint.setValue("http://localhost:8080/services/" + location+"?wsdl");
        myBindingTemplate.setAccessPoint(accessPoint);
        BindingTemplates myBindingTemplates = new BindingTemplates();

        myBindingTemplate = UDDIClient.addSOAPtModels(myBindingTemplate);
        myBindingTemplates.getBindingTemplate().add(myBindingTemplate);
        service.setBindingTemplates(myBindingTemplates);
    }
}
