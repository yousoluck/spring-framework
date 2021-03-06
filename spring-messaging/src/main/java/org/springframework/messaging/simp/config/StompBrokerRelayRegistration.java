/*
 * Copyright 2002-2013 the original author or authors.
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

package org.springframework.messaging.simp.config;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompBrokerRelayMessageHandler;
import org.springframework.util.Assert;


/**
 * A helper class for configuring a relay to an external STOMP message broker.
 *
 * @author Rossen Stoyanchev
 * @since 4.0
 */
public class StompBrokerRelayRegistration extends AbstractBrokerRegistration {

	private String relayHost = "127.0.0.1";

	private int relayPort = 61613;

	private String applicationLogin = "guest";

	private String applicationPasscode = "guest";


	public StompBrokerRelayRegistration(MessageChannel webSocketReplyChannel, String[] destinationPrefixes) {
		super(webSocketReplyChannel, destinationPrefixes);
	}


	/**
	 * Set the STOMP message broker host.
	 */
	public StompBrokerRelayRegistration setRelayHost(String relayHost) {
		Assert.hasText(relayHost, "relayHost must not be empty");
		this.relayHost = relayHost;
		return this;
	}

	/**
	 * @return the STOMP message broker host.
	 */
	protected String getRelayHost() {
		return this.relayHost;
	}

	/**
	 * Set the STOMP message broker port.
	 */
	public StompBrokerRelayRegistration setRelayPort(int relayPort) {
		this.relayPort = relayPort;
		return this;
	}

	/**
	 * @return the STOMP message broker port.
	 */
	protected int getRelayPort() {
		return this.relayPort;
	}

	/**
	 * Set the login for a "system" TCP connection used to send messages to the STOMP
	 * broker without having a client session (e.g. REST/HTTP request handling method).
	 */
	public StompBrokerRelayRegistration setApplicationLogin(String login) {
		Assert.hasText(login, "applicationLogin must not be empty");
		this.applicationLogin = login;
		return this;
	}

	/**
	 * @return the login for a shared, "system" connection to the STOMP message broker.
	 */
	protected String getApplicationLogin() {
		return this.applicationLogin;
	}

	/**
	 * Set the passcode for a "system" TCP connection used to send messages to the STOMP
	 * broker without having a client session (e.g. REST/HTTP request handling method).
	 */
	public StompBrokerRelayRegistration setApplicationPasscode(String passcode) {
		Assert.hasText(passcode, "applicationPasscode must not be empty");
		this.applicationPasscode = passcode;
		return this;
	}

	/**
	 * @return the passcode for a shared, "system" connection to the STOMP message broker.
	 */
	protected String getApplicationPasscode() {
		return this.applicationPasscode;
	}


	protected StompBrokerRelayMessageHandler getMessageHandler() {
		StompBrokerRelayMessageHandler handler =
				new StompBrokerRelayMessageHandler(getWebSocketReplyChannel(), getDestinationPrefixes());
		handler.setRelayHost(this.relayHost);
		handler.setRelayPort(this.relayPort);
		handler.setSystemLogin(this.applicationLogin);
		handler.setSystemPasscode(this.applicationPasscode);
		return handler;
	}

}
