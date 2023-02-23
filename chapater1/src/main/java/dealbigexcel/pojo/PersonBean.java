package dealbigexcel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author stuminker
 * @version 1.0
 * @project normal
 * @description 人员对象
 * @date 2023/2/22 22:06:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonBean {
    private String name;
    private String sex;
    private String birthday;
    private String province;
    private String city;
    private String idType;
    private String idCard;
    private String picAddress;

    @Override
    public String toString() {
        return "PersonBean{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", idType='" + idType + '\'' +
                ", idCard='" + idCard + '\'' +
                ", picAddress='" + picAddress + '\'' +
                '}';
    }
}
