package example.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName article
 */
@TableName(value ="article")
@Data
public class Article implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer aid;

    /**
     * 
     */
    private String articlename;

    /**
     * 
     */
    private String articleintro;

    /**
     * 
     */
    private String articlecontent;

    /**
     * 
     */
    private String articlephoto;

    /**
     * 创建时间
     */
    private Date articlecreatetime;

    /**
     * 
     */
    private Integer cid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Article other = (Article) that;
        return (this.getAid() == null ? other.getAid() == null : this.getAid().equals(other.getAid()))
            && (this.getArticlename() == null ? other.getArticlename() == null : this.getArticlename().equals(other.getArticlename()))
            && (this.getArticleintro() == null ? other.getArticleintro() == null : this.getArticleintro().equals(other.getArticleintro()))
            && (this.getArticlecontent() == null ? other.getArticlecontent() == null : this.getArticlecontent().equals(other.getArticlecontent()))
            && (this.getArticlephoto() == null ? other.getArticlephoto() == null : this.getArticlephoto().equals(other.getArticlephoto()))
            && (this.getArticlecreatetime() == null ? other.getArticlecreatetime() == null : this.getArticlecreatetime().equals(other.getArticlecreatetime()))
            && (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAid() == null) ? 0 : getAid().hashCode());
        result = prime * result + ((getArticlename() == null) ? 0 : getArticlename().hashCode());
        result = prime * result + ((getArticleintro() == null) ? 0 : getArticleintro().hashCode());
        result = prime * result + ((getArticlecontent() == null) ? 0 : getArticlecontent().hashCode());
        result = prime * result + ((getArticlephoto() == null) ? 0 : getArticlephoto().hashCode());
        result = prime * result + ((getArticlecreatetime() == null) ? 0 : getArticlecreatetime().hashCode());
        result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", aid=").append(aid);
        sb.append(", articlename=").append(articlename);
        sb.append(", articleintro=").append(articleintro);
        sb.append(", articlecontent=").append(articlecontent);
        sb.append(", articlephoto=").append(articlephoto);
        sb.append(", articlecreatetime=").append(articlecreatetime);
        sb.append(", cid=").append(cid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}