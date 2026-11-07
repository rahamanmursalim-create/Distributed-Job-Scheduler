package io.distributedjobscheduler.server.common.actor;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class PropsFactoryManager extends AbstractExtensionId<PropsFactory> {
    private static PropsFactoryManager provider = new PropsFactoryManager();

    public static PropsFactoryManager getFactory() {
        return provider;
    }

    @Override
    public PropsFactory createExtension(ExtendedActorSystem system) {
        return new PropsFactory();
    }
}
