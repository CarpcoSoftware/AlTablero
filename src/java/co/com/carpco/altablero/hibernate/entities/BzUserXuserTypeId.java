package co.com.carpco.altablero.hibernate.entities;
// Generated 24-sep-2014 23:08:01 by Hibernate Tools 4.3.1



/**
 * BzUserXuserTypeId generated by hbm2java
 */
public class BzUserXuserTypeId  implements java.io.Serializable {


     private int idUser;
     private int idUserType;

    public BzUserXuserTypeId() {
    }

    public BzUserXuserTypeId(int idUser, int idUserType) {
       this.idUser = idUser;
       this.idUserType = idUserType;
    }
   
    public int getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public int getIdUserType() {
        return this.idUserType;
    }
    
    public void setIdUserType(int idUserType) {
        this.idUserType = idUserType;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BzUserXuserTypeId) ) return false;
		 BzUserXuserTypeId castOther = ( BzUserXuserTypeId ) other; 
         
		 return (this.getIdUser()==castOther.getIdUser())
 && (this.getIdUserType()==castOther.getIdUserType());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdUser();
         result = 37 * result + this.getIdUserType();
         return result;
   }   


}


