/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.carpco.altablero.bo;

import co.com.carpco.altablero.hibernate.entities.BzUser;
import co.com.carpco.altablero.hibernate.entities.BzUserXuserType;
import co.com.carpco.altablero.hibernate.entities.CnUserType;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Carlos Rodriguez
 */
public class User {

    private Integer id;

    private String documentNumber;
    
    private String documentType;
    
    private String name;
    
    private String lastName;
    
    private Date born;
    
    private String address;
    
    private long phone1;
    
    private Long phone2;
    
    private String password;
    
    private String gender;
    
    private Date creation;
    
    private Date updated;
    
    private boolean enabled;
    
    private Set<UserType> userTypeSet;

    public User(BzUser bzUser) {
        super();
        this.id = bzUser.getId();
        this.documentNumber = bzUser.getDocumentNumber();
        this.documentType = bzUser.getDocumentType();
        this.name = bzUser.getName();
        this.lastName = bzUser.getLastName();
        this.born = bzUser.getBorn();
        this.address = bzUser.getAddress();
        this.phone1 = bzUser.getPhone1();
        this.phone2 = bzUser.getPhone2();
        this.password = bzUser.getPassword();
        this.gender = bzUser.getGender();
        this.creation = bzUser.getCreation();
        this.updated = bzUser.getUpdated();
        this.enabled = bzUser.isEnabled();
        this.userTypeSet = new HashSet<>();
        
        Set<BzUserXuserType> bzUserXUserTypeSet = bzUser.getBzUserXuserTypes();
        bzUserXUserTypeSet.stream().forEach((bzUserXUserType) -> {
            userTypeSet.add(new UserType(bzUserXUserType.getCnUserType()));
        });
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the documentNumber
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * @param documentNumber the documentNumber to set
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * @return the documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * @param documentType the documentType to set
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the born
     */
    public Date getBorn() {
        return born;
    }

    /**
     * @param born the born to set
     */
    public void setBorn(Date born) {
        this.born = born;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone1
     */
    public long getPhone1() {
        return phone1;
    }

    /**
     * @param phone1 the phone1 to set
     */
    public void setPhone1(long phone1) {
        this.phone1 = phone1;
    }

    /**
     * @return the phone2
     */
    public Long getPhone2() {
        return phone2;
    }

    /**
     * @param phone2 the phone2 to set
     */
    public void setPhone2(Long phone2) {
        this.phone2 = phone2;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the creation
     */
    public Date getCreation() {
        return creation;
    }

    /**
     * @param creation the creation to set
     */
    public void setCreation(Date creation) {
        this.creation = creation;
    }

    /**
     * @return the updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated the updated to set
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the userTypeSet
     */
    public Set<UserType> getUserTypeSet() {
        return userTypeSet;
    }

    /**
     * @param userTypeSet the userTypeSet to set
     */
    public void setUserTypeSet(Set<UserType> userTypeSet) {
        this.userTypeSet = userTypeSet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.documentNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.documentNumber, other.documentNumber)) {
            return false;
        }
        if (!Objects.equals(this.documentType, other.documentType)) {
            return false;
        }
        return true;
    }
}