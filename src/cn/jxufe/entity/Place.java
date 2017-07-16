package cn.jxufe.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 景点实体
 */
@Entity
@Table(name = "T_Place")
public class Place implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private City city;
	private String name;
	private String desc;
	private Float price;
	private String opening;
	private String photo;
	private String photoA;
	private String photoB;
	private String photoC;
	private String address;
	private String type;
	private String level;
	private String intro;
	private Set<Comment> comment = new HashSet<Comment>();

	@Id
	@Column(name = "placeId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "cityId", nullable = false)
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(name = "placeName", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "placeDesc", length=2550)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "placePrice")
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Column(name = "placeOpening")
	public String getOpening() {
		return opening;
	}

	public void setOpening(String opening) {
		this.opening = opening;
	}

	@Column(name = "placePhoto")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name = "placePhotoA")
	public String getPhotoA() {
		return photoA;
	}

	public void setPhotoA(String photoA) {
		this.photoA = photoA;
	}

	@Column(name = "placePhotoB")
	public String getPhotoB() {
		return photoB;
	}

	public void setPhotoB(String photoB) {
		this.photoB = photoB;
	}

	@Column(name = "placePhotoC")
	public String getPhotoC() {
		return photoC;
	}

	public void setPhotoC(String photoC) {
		this.photoC = photoC;
	}
	@Column(name = "placeAddress")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "placeType")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "placeLevel")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "placeIntro")
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "place")
	@OrderBy("id ASC")
	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}

}
