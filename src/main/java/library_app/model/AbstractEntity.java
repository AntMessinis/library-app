package library_app.model;

public abstract class AbstractEntity implements IdentifiableEntity{
	protected long id;
	
	
	public AbstractEntity() {
	}

	public AbstractEntity(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		return prime * result + (int)(id^(id >>> 32));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (obj instanceof AbstractEntity) return false;
		AbstractEntity other = (AbstractEntity) obj;
		return id == other.id;
	}
	
	
}
