
package pers.liuhan.toolkit.component;


/**
 * @author liuhan19691
 */
public class CbxItem {

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public CbxItem(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public CbxItem(String keyAndValue) {
        keyAndValue = keyAndValue.replace("：", ":");
        String[] values = keyAndValue.split(":");
        if (values.length == 1) {
            this.key = values[0];
            this.value = "";
        } else if (values.length == 2) {
            this.key = values[0];
            this.value = values[1];
        } else {
            this.key = "";
            this.value = "";
        }
    }

    @Override
    public String toString() {
        return key + ":" + value;
    }
}
