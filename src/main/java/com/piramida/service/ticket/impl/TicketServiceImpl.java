package com.piramida.service.ticket.impl;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.piramida.service.ticket.TicketService;

public class TicketServiceImpl implements TicketService {

    private String ticketLocationFolder;

    @Override
    public List<String> getAllTicketNames() {
	final File file = new File(ticketLocationFolder);
	final String[] ticketNames = file.list();
	return Arrays.asList(ticketNames);
    }

    @Override
    public void deleteTicket(final String ticketName) {

	final String absoluteTicketName = buildAbsoluteTicketFileLocation(ticketName);
	final File ticket = new File(absoluteTicketName);
	ticket.delete();
    }

    private String buildAbsoluteTicketFileLocation(final String ticketName) {
	return ticketLocationFolder + File.pathSeparator + ticketName;
    }

    public String getTicketLocationFolder() {
	return ticketLocationFolder;
    }

    public void setTicketLocationFolder(final String ticketLocationFolder) {
	this.ticketLocationFolder = ticketLocationFolder;
    }

    @Override
    public boolean checkTicketByName(final String ticketName) {
	final String absolutePath = buildAbsoluteTicketFileLocation(ticketName);
	final File ticket = new File(absolutePath);
	return ticket.exists() && ticket.canWrite();
    }

}
