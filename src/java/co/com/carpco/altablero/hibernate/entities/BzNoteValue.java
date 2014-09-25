package co.com.carpco.altablero.hibernate.entities;
// Generated 24-sep-2014 23:08:01 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * BzNoteValue generated by hbm2java
 */
public class BzNoteValue  implements java.io.Serializable {


     private BzNoteValueId id;
     private BigDecimal value;
     private Date creation;
     private Date updated;
     private boolean enabled;

    public BzNoteValue() {
    }

    public BzNoteValue(BzNoteValueId id, BigDecimal value, Date creation, Date updated, boolean enabled) {
       this.id = id;
       this.value = value;
       this.creation = creation;
       this.updated = updated;
       this.enabled = enabled;
    }
   
    public BzNoteValueId getId() {
        return this.id;
    }
    
    public void setId(BzNoteValueId id) {
        this.id = id;
    }
    public BigDecimal getValue() {
        return this.value;
    }
    
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public Date getCreation() {
        return this.creation;
    }
    
    public void setCreation(Date creation) {
        this.creation = creation;
    }
    public Date getUpdated() {
        return this.updated;
    }
    
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }




}

