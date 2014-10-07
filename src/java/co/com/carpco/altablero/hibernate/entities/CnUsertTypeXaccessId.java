package co.com.carpco.altablero.hibernate.entities;
// Generated 07-oct-2014 8:22:25 by Hibernate Tools 4.3.1



/**
 * CnUsertTypeXaccessId generated by hbm2java
 */
public class CnUsertTypeXaccessId  implements java.io.Serializable {


     private int idUserType;
     private int idAccess;

    public CnUsertTypeXaccessId() {
    }

    public CnUsertTypeXaccessId(int idUserType, int idAccess) {
       this.idUserType = idUserType;
       this.idAccess = idAccess;
    }
   
    public int getIdUserType() {
        return this.idUserType;
    }
    
    public void setIdUserType(int idUserType) {
        this.idUserType = idUserType;
    }
    public int getIdAccess() {
        return this.idAccess;
    }
    
    public void setIdAccess(int idAccess) {
        this.idAccess = idAccess;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CnUsertTypeXaccessId) ) return false;
		 CnUsertTypeXaccessId castOther = ( CnUsertTypeXaccessId ) other; 
         
		 return (this.getIdUserType()==castOther.getIdUserType())
 && (this.getIdAccess()==castOther.getIdAccess());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdUserType();
         result = 37 * result + this.getIdAccess();
         return result;
   }   


}


