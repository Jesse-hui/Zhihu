package effectmobi.com.zhihu.effectmobi.com.zhihu.bean;

/**
 * User: Jesse Pinkman
 * Date: 2015-12-11
 * Time: 14:54
 */

public class MainItem {

    private String title;
    private int resId;

    public MainItem(String title, int resId) {
        this.title = title;
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "MainItem{" +
                "title='" + title + '\'' +
                ", resId=" + resId +
                '}';
    }
}