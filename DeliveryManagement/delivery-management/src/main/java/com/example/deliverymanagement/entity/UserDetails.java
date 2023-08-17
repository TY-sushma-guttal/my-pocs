package com.example.deliverymanagement.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_details")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	private String userName;

	private Long phoneNumber;

	private String emailId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userDetails", fetch = FetchType.LAZY)
	private List<DeliveryDetails> addressDetailsList;

}
