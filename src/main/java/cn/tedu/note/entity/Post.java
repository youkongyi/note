package cn.tedu.note.entity;

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	private String content;
	private Integer id;
	private Person person;
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	private List<Comment> comments;
	public Post() {
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Post(String content, Integer id, Person person, List<Comment> comments) {
		super();
		this.content = content;
		this.id = id;
		this.person = person;
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "Post [content=" + content + ", id=" + id + ", person=" + person + ", comments=" + comments + "]";
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public Post(String content, Integer id, List<Comment> comments) {
		super();
		this.content = content;
		this.id = id;
		this.comments = comments;
	}
	
}
