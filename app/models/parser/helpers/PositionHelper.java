package models.parser.helpers;

public class PositionHelper {

    private  Long left = null;
    private  Long top = 100000L;
    private  Long width = 0L;
    private  Long height = 0L;
    private  String value = "";
    private  Double distance = null;

    public PositionHelper(Long left, Long top, Long width, Long bottom, String value){
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = bottom;
        this.value = value;
    }
    public PositionHelper(){}
    public Long getLeft() {
        return left;
    }

    public Long getTop() {
        return top;
    }

    public Long getWidth() {
        return width;
    }

    public Long getHeight() {
        return height;
    }

    public String getValue(){
        return value;
    }

    public void setLeft(Long left) {
        this.left = left;
    }

    public void setTop(Long top) {
        this.top = top;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
    public PositionHelper reset(){
        this.left = null;
        this.top = 100000L;
        this.width = 0L;
        this.height = 0L;
        this.value = "";
        return this;
    }

    public PositionHelper updateValues(Long left, Long top, Long right, Long bottom, String value){
        if(this.left == null){
            this.left = left;
        }
        if(this.top > top){
            this.top = top;
        }
        this.width = right - this.left;

        if(this.height + this.top < bottom){
            this.height = bottom - top;
        }
        this.value = this.value + value;
        return this;
    }
}
