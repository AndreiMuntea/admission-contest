package domain.DTO;

/**
 * Created by andrei on 12/3/2016.
 */
public class TopSectionsByNumberDTO {
    private String sectionName;
    private Integer numberOfCandidates;

    public TopSectionsByNumberDTO(String sectionName, Integer numberOfCandidates) {
        this.sectionName = sectionName;
        this.numberOfCandidates = numberOfCandidates;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getNumberOfCandidates() {
        return numberOfCandidates;
    }

    public void setNumberOfCandidates(Integer numberOfCandidates) {
        this.numberOfCandidates = numberOfCandidates;
    }
}
