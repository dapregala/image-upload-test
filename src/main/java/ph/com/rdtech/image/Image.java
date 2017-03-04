package ph.com.rdtech.image;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Daniel on 04/03/2017.
 */
@Entity
@Table(name = "image")
public class Image {

    @Id
    @NotNull
    private String imageType;

    @NotNull
    private String url;

    public Image() {} // No args constructor

    public Image(String imageType, String url) {
        this.imageType = imageType;
        this.url = url;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
