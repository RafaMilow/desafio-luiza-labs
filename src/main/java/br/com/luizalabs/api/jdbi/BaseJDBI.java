package br.com.luizalabs.api.jdbi;

import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.tweak.HandleCallback;

public abstract class BaseJDBI<B> {

	private Class<B> binderClazz;

	public BaseJDBI(Class<B> clazz) {
		this.binderClazz = clazz;
	}

	protected B getBinder(Handle handle) {
		return handle.attach(binderClazz);
	}

	protected <T> T handler(HandleCallback<T> callback) {
		return DataBaseResources.handler(callback);
	}

}
