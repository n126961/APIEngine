package com.api.model.es;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ESSearchResponse {
	@SerializedName("hits")
	@Expose
	private Hits hits;

	public Hits getHits() {
		return hits;
	}

	public void setHits(Hits hits) {
		this.hits = hits;
	}
	
	
	
	public List<String> getFormattedResponse() {
		List<String> formatted = new ArrayList<String>();
		for(Hit hit:this.getHits().getHits()) {
			formatted.add(hit.getSource().getFormatted());
		}
		
		return formatted;
	}
	
	public Map<String,String> getIndexNames(){
		Map<String,String> names = new HashMap<String, String>();
		for(Hit hit:this.getHits().getHits()) {
			names.put(hit.getSource().getIndexName(), hit.getSource().getIndexLabel());
		}
		return names;
	}
	
	public Map<String,String> getAggsForIndex(){
		Map<String,String> names = new HashMap<String, String>();
		for(Hit hit:this.getHits().getHits()) {
			names.put(hit.getSource().getIndexName(), "{"+hit.getSource().getAggs()+"}");
		}
		return names;
	}
}

class Hits {

	@SerializedName("hits")
	@Expose
	private List<Hit> hits = null;

	public List<Hit> getHits() {
		return hits;
	}

	public void setHits(List<Hit> hits) {
		this.hits = hits;
	}

}

class Hit {

	@SerializedName("_source")
	@Expose
	private Source source;

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

}

class Source {

	@SerializedName("formatted")
	@Expose
	private String formatted;
	@SerializedName("indexName")
	@Expose
	private String indexName;
	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getIndexLabel() {
		return indexLabel;
	}

	public void setIndexLabel(String indexLabel) {
		this.indexLabel = indexLabel;
	}

	public String getAggs() {
		return aggs;
	}

	public void setAggs(String aggs) {
		this.aggs = aggs;
	}

	@SerializedName("indexLabel")
	@Expose
	private String indexLabel;
	@SerializedName("aggs")
	@Expose
	private String aggs;

	public String getFormatted() {
		return formatted;
	}

	public void setFormatted(String formatted) {
		this.formatted = formatted;
	}

}
