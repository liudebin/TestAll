/**
 * Copyright 2012 Netflix, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package qian.ling.yi.ext.hystrix.examples.basic;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

/**
 * Sample {@link HystrixCollapser} that automatically batches multiple requests to execute()/queue()
 * into a single {@link HystrixCommand} execution for all requests within the defined batch (time or size).
 * 使用场景:HystrixCollapser用于对多个相同业务的请求合并到一个线程甚至可以合并到一个连接中执行，降低线程交互次和IO数,但必须保证他们属于同一依赖.
 */
public class CommandCollapserGetValueForKey extends HystrixCollapser<List<String>, String, Integer> {

    private final Integer key;

    public CommandCollapserGetValueForKey(Integer key) {
        this.key = key;
    }

    @Override
    public Integer getRequestArgument() {
        return key;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(final Collection<CollapsedRequest<String, Integer>> requests) {
        return new BatchCommand(requests);
    }

    @Override
    protected void mapResponseToRequests(List<String> batchResponse, Collection<CollapsedRequest<String, Integer>> requests) {
        int count = 0;
        for (CollapsedRequest<String, Integer> request : requests) {
            request.setResponse(batchResponse.get(count++));
        }
    }

    private static final class BatchCommand extends HystrixCommand<List<String>> {
        private final Collection<CollapsedRequest<String, Integer>> requests;

        private BatchCommand(Collection<CollapsedRequest<String, Integer>> requests) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueForKey")));
            this.requests = requests;
        }

        @Override
        protected List<String> run() {
            ArrayList<String> response = new ArrayList<String>();
            for (CollapsedRequest<String, Integer> request : requests) {
                // artificial response for each argument received in the batch
                response.add("ValueForKey: " + request.getArgument());
            }
            return response;
        }
    }

    public static class UnitTest {

        @Test
        public void testCollapser() throws Exception {
            HystrixRequestContext context = HystrixRequestContext.initializeContext();
            try {
                Future<String> f1 = new CommandCollapserGetValueForKey(1).queue();
//                Future<String> f2 = new CommandCollapserGetValueForKey(2).queue();
//                Future<String> f3 = new CommandCollapserGetValueForKey(3).queue();
//                Future<String> f4 = new CommandCollapserGetValueForKey(4).queue();
//                Future<String> f5 = new CommandCollapserGetValueForKey(5).queue();
//                Future<String> f6 = new CommandCollapserGetValueForKey(6).queue();
//                Future<String> f7 = new CommandCollapserGetValueForKey(7).queue();
//                Future<String> f8 = new CommandCollapserGetValueForKey(8).queue();
//                Future<String> f9 = new CommandCollapserGetValueForKey(9).queue();
//                Future<String> f10 = new CommandCollapserGetValueForKey(10).queue();

                assertEquals("ValueForKey: 1", f1.get());
//                assertEquals("ValueForKey: 2", f2.get());
//                assertEquals("ValueForKey: 3", f3.get());
//                assertEquals("ValueForKey: 4", f4.get());
//                assertEquals("ValueForKey: 5", f5.get());
//                assertEquals("ValueForKey: 6", f6.get());
//                assertEquals("ValueForKey: 7", f7.get());
//                assertEquals("ValueForKey: 8", f8.get());
//                assertEquals("ValueForKey: 9", f9.get());
//                assertEquals("ValueForKey: 10", f10.get());

                int numExecuted = HystrixRequestLog.getCurrentRequest().getAllExecutedCommands().size();

                System.err.println("num executed: " + numExecuted);

                // assert that the batch command 'GetValueForKey' was in fact executed and that it executed only 
                // once or twice (due to non-determinism of scheduler since this example uses the real timer)
                if (numExecuted > 2) {
                    fail("some of the commands should have been collapsed");
                }

                System.err.println("HystrixRequestLog.getCurrentRequest().getAllExecutedCommands(): " + HystrixRequestLog.getCurrentRequest().getAllExecutedCommands());

                int numLogs = 0;
                for (HystrixInvokableInfo<?> command : HystrixRequestLog.getCurrentRequest().getAllExecutedCommands()) {
                    numLogs++;
                    
                    // assert the command is the one we're expecting
                    assertEquals("GetValueForKey", command.getCommandKey().name());

                    System.err.println(command.getCommandKey().name() + " => command.getExecutionEvents(): " + command.getExecutionEvents());

                    // confirm that it was a COLLAPSED command execution
                    assertTrue(command.getExecutionEvents().contains(HystrixEventType.COLLAPSED));
                    assertTrue(command.getExecutionEvents().contains(HystrixEventType.SUCCESS));
                }

                assertEquals(numExecuted, numLogs);
            } finally {
                context.shutdown();
            }
        }
    }
}
