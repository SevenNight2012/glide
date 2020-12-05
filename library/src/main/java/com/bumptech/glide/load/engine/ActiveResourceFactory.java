package com.bumptech.glide.load.engine;

/**
 * ActiveResource对象的构造工厂
 */
public interface ActiveResourceFactory {

    class DefaultFactory implements ActiveResourceFactory{

        @Override
        public ActiveResources createResource(boolean isActiveResourceRetentionAllowed) {
            return new ActiveResources(isActiveResourceRetentionAllowed);
        }
    }

    ActiveResources createResource(boolean isActiveResourceRetentionAllowed);
}
