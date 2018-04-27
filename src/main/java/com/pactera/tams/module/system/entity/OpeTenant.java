package com.pactera.tams.module.system.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.pactera.tams.common.entity.BaseEntity;


/**
* 租户实体
* @Author: mjh
* @Date: 2018-03-19 16:06:42
*/
@Table(name = "ope_tenant")
public class OpeTenant extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 名称
     */
    private String name;

    /**
     * 简称
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 英文名称
     */
    private String enname;

    /**
     * 类型（预留）
     */
    private Byte type;

    /**
     * 所在省编码
     */
    @Column(name = "province_code")
    private String provinceCode;

    /**
     * 所在省名称
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 所在市编码
     */
    @Column(name = "city_code")
    private String cityCode;

    /**
     * 所在市名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 所在县/区编码
     */
    @Column(name = "district_code")
    private String districtCode;

    /**
     * 所在县/区名称
     */
    @Column(name = "district_name")
    private String districtName;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 公司电话
     */
    private String telephone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 营业执照路径
     */
    @Column(name = "business_licence")
    private String businessLicence;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    @Column(name = "contact_number")
    private String contactNumber;

    /**
     * 启用状态（1是，0否）
     */
    private Byte enabled;

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取简称
     *
     * @return short_name - 简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置简称
     *
     * @param shortName 简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取英文名称
     *
     * @return enname - 英文名称
     */
    public String getEnname() {
        return enname;
    }

    /**
     * 设置英文名称
     *
     * @param enname 英文名称
     */
    public void setEnname(String enname) {
        this.enname = enname;
    }

    /**
     * 获取类型（预留）
     *
     * @return type - 类型（预留）
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置类型（预留）
     *
     * @param type 类型（预留）
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取所在省编码
     *
     * @return province_code - 所在省编码
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * 设置所在省编码
     *
     * @param provinceCode 所在省编码
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * 获取所在省名称
     *
     * @return province_name - 所在省名称
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置所在省名称
     *
     * @param provinceName 所在省名称
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * 获取所在市编码
     *
     * @return city_code - 所在市编码
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 设置所在市编码
     *
     * @param cityCode 所在市编码
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 获取所在市名称
     *
     * @return city_name - 所在市名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置所在市名称
     *
     * @param cityName 所在市名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 获取所在县/区编码
     *
     * @return district_code - 所在县/区编码
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * 设置所在县/区编码
     *
     * @param districtCode 所在县/区编码
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * 获取所在县/区名称
     *
     * @return district_name - 所在县/区名称
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * 设置所在县/区名称
     *
     * @param districtName 所在县/区名称
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * 获取详细地址
     *
     * @return address - 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细地址
     *
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取公司电话
     *
     * @return telephone - 公司电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置公司电话
     *
     * @param telephone 公司电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取传真
     *
     * @return fax - 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真
     *
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 获取营业执照路径
     *
     * @return business_licence - 营业执照路径
     */
    public String getBusinessLicence() {
        return businessLicence;
    }

    /**
     * 设置营业执照路径
     *
     * @param businessLicence 营业执照路径
     */
    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    /**
     * 获取联系人
     *
     * @return contact - 联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系人
     *
     * @param contact 联系人
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取联系电话
     *
     * @return contact_number - 联系电话
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * 设置联系电话
     *
     * @param contactNumber 联系电话
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * 获取启用状态（1是，0否）
     *
     * @return enabled - 启用状态（1是，0否）
     */
    public Byte getEnabled() {
        return enabled;
    }

    /**
     * 设置启用状态（1是，0否）
     *
     * @param enabled 启用状态（1是，0否）
     */
    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }
}