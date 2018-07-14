package Model;

import java.util.UUID;

/**
 * Created by Vito Zhuang on 7/11/2018.
 */
public class Receipt {
    private String uuid;

    public Receipt(UUID uuid) {
        this.uuid = uuid.toString();
    }

    public String getUuid() {
        return uuid;
    }
}
