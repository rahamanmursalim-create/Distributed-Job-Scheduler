package io.distributedjobscheduler.worker.spring.boot;

import io.distributedjobscheduler.common.DistributedJobSchedulerSpringContext;
import io.distributedjobscheduler.worker.processor.ProcessResult;
import io.distributedjobscheduler.worker.processor.ProcessorHandler;
import io.distributedjobscheduler.worker.processor.ProcessorHandlerMapping;
import io.distributedjobscheduler.worker.spring.boot.annotation.DistributedJobScheduler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Slf4j
public class DistributedJobSchedulerSpringWorker implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
        if (Objects.isNull(DistributedJobSchedulerSpringContext.getApplicationContext())) {
            return;
        }

        // All bean names.
        String[] beanNames = DistributedJobSchedulerSpringContext.getBeanNamesForType(Object.class, false, true);
        for (String beanName : beanNames) {
            Object bean = DistributedJobSchedulerSpringContext.getBean(beanName);

            // Find distributedjobscheduler annotation method
            Map<Method, DistributedJobScheduler> annotatedMethods = null;
            try {
                annotatedMethods = MethodIntrospector.selectMethods(bean.getClass(),
                        (MethodIntrospector.MetadataLookup<DistributedJobScheduler>) method -> AnnotatedElementUtils.findMergedAnnotation(method, DistributedJobScheduler.class)
                );
            } catch (Throwable ex) {
                log.error(String.format("Processor handler resolve failed! beanName=%s beanClass=%s", beanName, beanName.getClass()), ex);
            }

            // Not find annotation
            if (CollectionUtils.isEmpty(annotatedMethods)) {
                continue;
            }

            for (Map.Entry<Method, DistributedJobScheduler> entry : annotatedMethods.entrySet()) {
                Method method = entry.getKey();
                DistributedJobScheduler distributedjobscheduler = entry.getValue();

                if (Objects.isNull(distributedjobscheduler)) {
                    continue;
                }

                // Register processor handler
                this.registerProcessorHandler(bean, method, distributedjobscheduler);
            }
        }
    }

    /**
     * Register processor handler
     *
     * @param bean    bean
     * @param method  method
     * @param distributedjobscheduler distributedjobscheduler
     */
    private void registerProcessorHandler(Object bean, Method method, DistributedJobScheduler distributedjobscheduler) {
        // Processor handler
        ProcessorHandler processorHandler = new ProcessorHandler();
        processorHandler.setTarget(bean);

        // Validate name
        String name = distributedjobscheduler.value();
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException(String.format("DistributedJobScheduler name can not be empty! class=%s method=%s", bean.getClass(), method.getName()));
        }

        // Bean has existed
        if (DistributedJobSchedulerSpringContext.containsBean(name)) {
            throw new RuntimeException(String.format("Bean of the distributedjobscheduler name has existed! class=%s method=%s name=%s", bean.getClass(), method.getName(), name));
        }

        // Has existed
        if (ProcessorHandlerMapping.hasProcessorHandler(name)) {
            throw new RuntimeException(String.format("DistributedJobScheduler name has existed! class=%s method=%s name=%s", bean.getClass(), method.getName(), name));
        }

        // Return type
        if (!method.getReturnType().isAssignableFrom(ProcessResult.class)) {
            throw new RuntimeException(String.format("Processor method must return ProcessResult! class=%s method=%s name=%s", bean.getClass(), method.getName(), name));
        }

        method.setAccessible(true);
        processorHandler.setProcessMethod(method);

        // Pre method
        String preMethodName = distributedjobscheduler.pre();
        if (!StringUtils.isEmpty(preMethodName)) {
            try {
                Method preMethod = bean.getClass().getMethod(preMethodName);
                preMethod.setAccessible(true);
                processorHandler.setPreMethod(preMethod);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(String.format("Pre method is undefined! method=%s", preMethodName), e);
            }
        }

        // Post method
        String postMethodName = distributedjobscheduler.post();
        if (!StringUtils.isEmpty(postMethodName)) {
            try {
                Method postMethod = bean.getClass().getMethod(postMethodName);
                postMethod.setAccessible(true);
                processorHandler.setPostMethod(postMethod);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(String.format("Post method is undefined! method=%s", preMethodName), e);
            }
        }

        // Stop method
        String stopMethodName = distributedjobscheduler.stop();
        if (!StringUtils.isEmpty(stopMethodName)) {
            try {
                Method stopMethod = bean.getClass().getMethod(stopMethodName);
                stopMethod.setAccessible(true);
                processorHandler.setStopMethod(stopMethod);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(String.format("Stop method is undefined! method=%s", preMethodName), e);
            }
        }

        ProcessorHandlerMapping.registerProcessorHandler(name, processorHandler);
    }
}
