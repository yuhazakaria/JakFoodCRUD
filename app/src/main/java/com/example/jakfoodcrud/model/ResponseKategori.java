package com.example.jakfoodcrud.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ResponseKategori{

	@SerializedName("DataKategori")
	private List<DataKategoriItem> dataKategori;

	public void setDataKategori(List<DataKategoriItem> dataKategori){
		this.dataKategori = dataKategori;
	}

	public List<DataKategoriItem> getDataKategori(){
		return dataKategori;
	}

	@Override
 	public String toString(){
		return 
			"ResponseKategori{" + 
			"dataKategori = '" + dataKategori + '\'' + 
			"}";
		}
}