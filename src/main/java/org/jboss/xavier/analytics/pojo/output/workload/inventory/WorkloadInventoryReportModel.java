package org.jboss.xavier.analytics.pojo.output.workload.inventory;

import org.jboss.xavier.analytics.pojo.output.AnalysisModel;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class WorkloadInventoryReportModel
{
    static final long serialVersionUID = 1L;

    public static final String MORE_THAN_4_NICS_FLAG_NAME = ">4 vNICs";
    public static final String RDM_DISK_FLAG_NAME = "RDM";
    public static final String SHARED_DISK_FLAG_NAME = "Shared Disk";

    public static final String COMPLEXITY_EASY = "Easy";
    public static final String COMPLEXITY_MEDIUM = "Medium";
    public static final String COMPLEXITY_HARD = "Hard";
    public static final String COMPLEXITY_UNKNOWN = "Unknown";

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "WORKLOADINVENTORYREPORTMODEL_ID_GENERATOR")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analysis_id")
    private AnalysisModel analysis;

    private String provider;
    private String datacenter;
    private String cluster;
    private String vmName;
    private String osName;
    private String osDescription;
    private Long diskSpace;
    private Long memory;
    private Integer cpuCores;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> workloads;
    private String complexity;
    // with "IMS" suffix in case one day we will have
    // their "AMM" counterparts
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> recommendedTargetsIMS;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> flagsIMS;
    private Date creationDate;
    private String product;
    private String version;
    private String host_name;
    private Boolean ssaEnabled;

    public WorkloadInventoryReportModel() {}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnalysisModel getAnalysis() {
        return analysis;
    }

    public void setAnalysis(AnalysisModel analysis) {
        this.analysis = analysis;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDatacenter() {
        return datacenter;
    }

    public void setDatacenter(String datacenter) {
        this.datacenter = datacenter;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getVmName() {
        return vmName;
    }

    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsDescription() {
        return osDescription;
    }

    public void setOsDescription(String osDescription) {
        this.osDescription = osDescription;
    }

    public Long getDiskSpace() {
        return diskSpace;
    }

    public void setDiskSpace(Long diskSpace) {
        this.diskSpace = diskSpace;
    }

    public Long getMemory() {
        return memory;
    }

    public void setMemory(Long memory) {
        this.memory = memory;
    }

    public Integer getCpuCores() {
        return cpuCores;
    }

    public void setCpuCores(Integer cpuCores) {
        this.cpuCores = cpuCores;
    }

    public Set<String> getWorkloads() {
        return workloads;
    }

    public void setWorkloads(Set<String> workloads) {
        this.workloads = workloads;
    }

    public void addWorkload(String workload)
    {
        if (this.workloads == null) this.workloads = new HashSet<>();
        this.workloads.add(workload);
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public Set<String> getRecommendedTargetsIMS() {
        return recommendedTargetsIMS;
    }

    public void setRecommendedTargetsIMS(Set<String> recommendedTargetsIMS) {
        this.recommendedTargetsIMS = recommendedTargetsIMS;
    }

    public void addRecommendedTargetsIMS(String recommendedTargetIMS)
    {
        if (this.recommendedTargetsIMS == null) this.recommendedTargetsIMS = new HashSet<>();
        this.recommendedTargetsIMS.add(recommendedTargetIMS);
    }

    public Set<String> getFlagsIMS() {
        return flagsIMS;
    }

    public void setFlagsIMS(Set<String> flagsIMS) {
        this.flagsIMS = flagsIMS;
    }

    public void addFlagIMS(String flagIMS)
    {
        if (this.flagsIMS == null) this.flagsIMS = new HashSet<>();
        this.flagsIMS.add(flagIMS);
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public Boolean getSsaEnabled() {
        return ssaEnabled;
    }

    public void setSsaEnabled(Boolean ssaEnabled) {
        this.ssaEnabled = ssaEnabled;
    }
}
