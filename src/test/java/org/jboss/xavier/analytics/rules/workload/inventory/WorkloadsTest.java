package org.jboss.xavier.analytics.rules.workload.inventory;

import org.jboss.xavier.analytics.pojo.input.workload.inventory.VMWorkloadInventoryModel;
import org.jboss.xavier.analytics.pojo.output.workload.inventory.WorkloadInventoryReportModel;
import org.jboss.xavier.analytics.rules.BaseTest;
import org.jboss.xavier.analytics.test.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.command.Command;
import org.kie.api.io.ResourceType;
import org.kie.internal.command.CommandFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkloadsTest extends BaseTest {

    public WorkloadsTest() {
        super("/org/jboss/xavier/analytics/rules/workload/inventory/Workloads.drl", ResourceType.DRL);
    }

    private void checkLoadedRulesNumber()
    {
        Utils.checkLoadedRulesNumber(kieSession, "org.jboss.xavier.analytics.rules.workload.inventory", 10);
    }

    @Test
    public void testTomcat() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("tomcat");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_Tomcat", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("tomcat")));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testEAP_1() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("jboss");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_EAP", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("Red Hat JBoss EAP".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testEAP_2() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("jboss-as-standalone.sh");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_EAP", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("Red Hat JBoss EAP".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testEAP_3() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("jboss-as-domain.sh");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_EAP", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("Red Hat JBoss EAP".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testEAP_4() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("jboss-eap-rhel.sh");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_EAP", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("Red Hat JBoss EAP".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testEAP_5() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("eap7-domain");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_EAP", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("Red Hat JBoss EAP".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testEAP_6() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("eap7-standalone");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_EAP", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("Red Hat JBoss EAP".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testEAP_7() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("jboss-host-controller");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_EAP", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("Red Hat JBoss EAP".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testWebsphere_1() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("Dmgr_was.init");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_Websphere", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("IBM Websphere App Server".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testWebsphere_2() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("Node_was.init");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_Websphere", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("IBM Websphere App Server".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testWebsphere_3() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("nodeagent_was.init");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_Websphere", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("IBM Websphere App Server".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }



    @Test
    public void testWebsphere_4() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("was");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_Websphere","SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("IBM Websphere App Server".toLowerCase())));
    }



    @Test
    public void testWebsphere_5() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("websphere");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_Websphere", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("IBM Websphere App Server".toLowerCase())));
    }

    @Test
    public void testWeblogic_1() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("wls_nodemanager");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_Weblogic", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("Oracle Weblogic".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testWeblogic_2() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("wls_adminmanager");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_Weblogic", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("Oracle Weblogic".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testWeblogic_3() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("weblogic_nodemanager");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_Weblogic", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("Oracle Weblogic".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testWeblogic_4() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("weblogic_adminmanager");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_Weblogic", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("Oracle Weblogic".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testOracleDB() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("dbora");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_Oracle_DB", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("Oracle Database".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testSAP_HANA() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("sapinit");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_SAP_HANA", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("SAP HANA".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testMSSQLServerOnLinux() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("unix_service");
        systemServicesNames.add("mssql-server");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat");
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_Microsoft_SQL_Server_On_Linux", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("Microsoft SQL Server".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }

    @Test
    public void testMSSQLServerOnWindows() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        Map<String, String> files = new HashMap<>();
        files.put("C:\\Program Files\\Microsoft SQL Server", null);
        vmWorkloadInventoryModel.setFiles(files);
        List<String> systemServicesNames = new ArrayList<>();
        systemServicesNames.add("NOTwas");
        vmWorkloadInventoryModel.setSystemServicesNames(systemServicesNames);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_Microsoft_SQL_Server_On_Windows", "SsaEnabled_System_Services_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("Microsoft SQL Server".toLowerCase())));
        Assert.assertTrue(report.getSsaEnabled());
    }



    @Test
    public void testMSSQLServerOnWindows2() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        Map<String, String> files = new HashMap<>();
        files.put("C:/Program Files/Microsoft SQL Server", null);
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();

        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(3, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "Workloads_Microsoft_SQL_Server_On_Windows","SsaDisabled_System_Services_Not_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNotNull(report.getWorkloads());
        Assert.assertEquals(1, report.getWorkloads().size());
        Assert.assertTrue(report.getWorkloads().stream().anyMatch(workload -> workload.toLowerCase().contains("Microsoft SQL Server".toLowerCase())));
    }

    @Test
    public void testFileWithNullContent() {
        checkLoadedRulesNumber();

        Map<String, Object> facts = new HashMap<>();
        // always add a String fact with the name of the agenda group defined in the DRL file (e.g. "SourceCosts")
        facts.put("agendaGroup", "Workloads");

        VMWorkloadInventoryModel vmWorkloadInventoryModel = new VMWorkloadInventoryModel();
        Map<String, String> files = new HashMap<>();
        files.put("file.txt", null);
        vmWorkloadInventoryModel.setFiles(files);
        facts.put("vmWorkloadInventoryModel", vmWorkloadInventoryModel);

        WorkloadInventoryReportModel workloadInventoryReportModel = new WorkloadInventoryReportModel();
        facts.put("workloadInventoryReportModel",workloadInventoryReportModel);

        Map<String, Object> results = createAndExecuteCommandsAndGetResults(facts);

        Assert.assertEquals(2, results.get(NUMBER_OF_FIRED_RULE_KEY));
        Utils.verifyRulesFiredNames(this.agendaEventListener, "AgendaFocusForTest", "SsaDisabled_System_Services_Not_Present");

        List<WorkloadInventoryReportModel> reports = extractModels(results, WorkloadInventoryReportModel.class);

        // just one report has to be created
        Assert.assertEquals(1, reports.size());
        WorkloadInventoryReportModel report = reports.get(0);
        Assert.assertNull(report.getWorkloads());
        Assert.assertFalse(report.getSsaEnabled());
    }

}

