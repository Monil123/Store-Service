package com.Store;

import java.io.Serializable;  
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "store") 

public class Store implements Serializable {  
   private static final long serialVersionUID = 1L; 
   private String id; 
   private String proName;		//Name of the product
   private int proPrice;  	//Price of the product
   private int quant;		//Quantity of the product
   public Store(){} 
    
   public Store(String id, String proName, int proPrice, int quant){  
      this.id = id; 
      this.proName = proName; 
      this.proPrice = proPrice;
      this.quant = quant;
   }  
   public String getId() { 				//Getters and Setters
      return id; 
   }  
   @XmlElement 
   public void setId(String id) { 
      this.id = id; 
   } 
   public String getProName() { 
      return proName; 
   } 
   @XmlElement
   public void setProName(String proName) { 
      this.proName = proName; 
   } 
   public int getProPrice() { 
      return proPrice; 
   } 
   @XmlElement 
   public void setProPrice(int proPrice) { 
      this.proPrice = proPrice; 
   }   
   public int getQuant(){
	   return quant;
   }
   @XmlElement
   public void setQuant(int quant){
	   this.quant = quant;
   }
} 