package domain.DTO;

/**
 * Created by andrei on 12/3/2016.
 */
public class TopSectionsByAverageDTO {
    private String sectionName;
    private Double average;

    public TopSectionsByAverageDTO(String sectionName, Double average) {
        this.sectionName = sectionName;
        this.average = average;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }
}
