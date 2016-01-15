package my.app;

import 	com.newrelic.api.agent.NewRelic;
import 	com.newrelic.api.agent.Trace;
import ratpack.server.RatpackServer;

public class Main {
    public static void main(String... args) throws Exception {
        RatpackServer.start(server -> server
                            .handlers(chain -> chain
                                      .get(ctx -> ctx.render("Hello World! NRJS: '"+getHeaderInTx()+"' fin."))
                                      .get(":name", ctx -> ctx.render("Hello " + ctx.getPathTokens().get("name") + "!"))
                                      )
                            );
    }

    @Trace(dispatcher = true)
    public static String getHeaderInTx() {
        String header = NewRelic.getBrowserTimingHeader();
        return header;
    }
}
