package snippet_splitter_out.ds_9$nc;
public class ds_9$nc_snip_1_logAndEmailSeriousProblemS113 {
public void logAndEmailSeriousProblemS113(Throwable ex, HttpServletRequest aRequest)
    {
    TroubleTicket troubleTicket = new TroubleTicket(ex, aRequest);
    fLogger.severe("TOP LEVEL CATCHING Throwable.");
    fLogger.severe(troubleTicket.toString());
    System.out.println("SERIOUS PROBLEM OCCURRED.");// changed to allow compilation
    System.out.println(troubleTicket.toString());// changed to allow compilation
    aRequest.getSession().getServletContext().
    setAttribute(MOST_RECENT_TROUBLE_TICKET, troubleTicket);
    troubleTicket.toString(); // changed to allow compilation
    }
}