package cn.jxufe.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 玩家实体
 */
@Entity
@Table(name = "T_User")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String account;
	private String nickname;
	private String password;
	private String phone;
	private String avatar;
	private Date regTime;
	private Integer age;
	private String gender;
	private String mail;
	private Set<Place> collectPlace;
	private Set<Order> order;
	
	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userAccount", unique = true, nullable = false, length = 16)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "userPassword", nullable = false, length = 16)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "userNickname", length = 16)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "userPhone", length = 11)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "userAvatar")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Column(name = "userRegTime", columnDefinition = "datetime default (getdate())")
	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	@Column(name = "userAge")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "userGender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "userMail")
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "T_UserPlace_Collect", 
			joinColumns = { @JoinColumn(name = "userId", referencedColumnName = "userId") }, 
			inverseJoinColumns = { @JoinColumn(name = "placeId", referencedColumnName = "placeId") }
			)
	public Set<Place> getCollectPlace() {
		return collectPlace;
	}

	public void setCollectPlace(Set<Place> collectPlace) {
		this.collectPlace = collectPlace;
	}

	@OneToMany(fetch=FetchType.LAZY,mappedBy="user")
	@OrderBy("id ASC")
	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}
	
	
}
