package com.StudentLoginv01.StudentLoginv01.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
})
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  public String name;
  
  @Size(max = 20)
  public String surname;
  
  @Size(max = 50)
  public String username;

  @NotBlank
  @Size(max = 50)
  @Email
  public String email;

  @NotBlank
  public String password;

  public String image;
  
  public String video;
  
  public String certification;
  
  public String headline;
  
  public String description;
  
  public String country;
  
  public String language;
  
  public Integer rate;
  
  public String role;

  public String status;
  
  @CreationTimestamp
  public LocalDateTime createdAt;
  
  @UpdateTimestamp
  public LocalDateTime updatedAt;

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "role_id", nullable = true)
//  private Role role;
//  
//  public ERole roleName;

  public User() {
  }

  public User(String name, String surname, String email, String password, String country, String language, String headline, String description, String image, String video, String certificaiton, Integer rate, String role) {
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = password;
    this.country = country;
    this.language = language;
    this.headline = headline;
    this.description = description;
    this.image = image;
    this.video = video;
    this.certification = certificaiton;
    this.rate = rate;
    this.role = role;
  }

  public User(Long id, String name, String email, String password, String image, String role) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.image = image;
    this.role = role;
  }
  

  public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public LocalDateTime getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
}

public String getLanguage() {
	return language;
}

public void setUpdatedAt(LocalDateTime updatedAt) {
	this.updatedAt = updatedAt;
}

public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public String getSurname() {
	  return surname;
  }
  
  public void setSurname(String surname) {
	  this.surname = surname;
  }
  
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
  
  public String getVideo() {
	  return video;
  }
  
  public void setVideo(String video) {
	  this.video = video;
  }
  
  public String getCertification() {
	  return certification;
  }
  
  public void setCertification(String certification) {
	  this.certification = certification;
  }
  
  public String getCountry() {
	  return country;
  }
  
  public void setCountry(String country) {
	  this.country = country;
  }
  
  public String getLangauge() {
	  return language;
  }
  
  public void setLanguage(String language) {
	  this.language = language;
  }
  
  public String getHeadline() {
	  return headline;
  }
  
  public void setHeadline(String headline) {
	  this.headline = headline;
  }
  
  public String getDescription() {
	  return description;
  }
  
  public void setDescription(String description) {
	  this.description = description;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Integer getRate() {
    return rate;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }
  
  public LocalDateTime getCreatedAd() {
	  return createdAt;
  }
  
  public LocalDateTime getUpdatedAt() {
	  return updatedAt;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", image='" + image + '\'' +
            ", video='" + video + '\'' +
            ", certification='" + certification + '\'' +
            ", headline='" + headline + '\'' +
            ", description='" + description + '\'' +
            ", country='" + country + '\'' +
            ", language='" + language + '\'' +
            ", rate=" + rate +
            ", role='" + role + '\'' +
            ", status='" + status + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
  }
}
