package com.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class GoodByeTasklet implements Tasklet {
    public static final String GOOD_BYE = "Goodbye, %s";

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        String name = (String) chunkContext.getStepContext().getJobParameters().get("name");
        ExecutionContext jobContext = chunkContext
                .getStepContext()
                .getStepExecution()
                .getExecutionContext();
        jobContext.put("user.name", name);
        System.out.println(String.format(GOOD_BYE, name));
        return RepeatStatus.FINISHED;
    }
}
