package com.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class HelloWorldTasklet implements Tasklet {
    public static final String HELLO_WOELD = "Hello, %s";

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        String name = (String) chunkContext.getStepContext().getJobParameters().get("name");
        System.out.println(stepContribution.getStepExecution().getStepName());
        ExecutionContext jobContext = chunkContext
                .getStepContext()
                .getStepExecution()
                .getExecutionContext();
        jobContext.put("user.name", name);
        System.out.println(String.format(HELLO_WOELD, name));
        return RepeatStatus.FINISHED;
    }
}
