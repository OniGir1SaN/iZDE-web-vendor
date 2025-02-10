package com.demoqa.entities.iZDEvendor.CreateObjectEntity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Getter
@Setter

public class FacilityDescriptionEntity {

    private String objectNameInput;
    private String objectDescriptionInput;
    private String typesOfPlacementsBtn;
    private String levelPlacementsBtn;
    private String[] downloadPictures;

    public String[] getDownloadPictures() {
        return downloadPictures;
    }

    public void setDownloadPictures(String[] downloadPictures) {
        this.downloadPictures = downloadPictures;
    }

}


