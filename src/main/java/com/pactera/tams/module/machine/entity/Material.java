package  com.pactera.tams.module.machine.entity;

import javax.persistence.Table;

import com.pactera.tams.common.model.BaseEntity;
/**
 * 材质
 * @author ljp
 *
 */
@Table(name = "tams_material")
public class Material extends BaseEntity{
	
	private String material_quality_code = null;
	
	private String material_quality_name = null;
	
	private String material_quality_spec = null;
	
	private String brand = null;
	
	private String supplier = null;
	
	private String status = null;
	
	private String tenant_id =null;

	public String getMaterial_quality_code() {
		return material_quality_code;
	}

	public void setMaterial_quality_code(String material_quality_code) {
		this.material_quality_code = material_quality_code;
	}

	public String getMaterial_quality_name() {
		return material_quality_name;
	}

	public void setMaterial_quality_name(String material_quality_name) {
		this.material_quality_name = material_quality_name;
	}

	public String getMaterial_quality_spec() {
		return material_quality_spec;
	}

	public void setMaterial_quality_spec(String material_quality_spec) {
		this.material_quality_spec = material_quality_spec;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}
	
}

