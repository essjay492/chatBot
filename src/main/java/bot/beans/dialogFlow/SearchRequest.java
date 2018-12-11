package bot.beans.dialogFlow;

public class SearchRequest {
    private String url;
    private String text;
    private String maxResults;
    private String sort;
    private String startOffSet;
    private String storeId;
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getMaxResults() {
        return maxResults;
    }
    public void setMaxResults(String maxResults) {
        this.maxResults = maxResults;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getStartOffSet() {
        return startOffSet;
    }
    public void setStartOffSet(String startOffSet) {
        this.startOffSet = startOffSet;
    }
    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    @Override
    public String toString() {
        return "SearchRequest [url=" + url + ", text=" + text + ", maxResults=" + maxResults + ", sort=" + sort
                + ", startOffSet=" + startOffSet + ", storeId=" + storeId + "]";
    }
    
}
