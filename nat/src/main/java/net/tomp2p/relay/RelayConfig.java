package net.tomp2p.relay;

import net.tomp2p.relay.android.GCMServerCredentials;

/**
 * Holds multiple relay types with their configuration
 * 
 * @author Nico Rutishauser
 *
 */
public class RelayConfig {

	private final RelayType type;
	private final GCMServerCredentials gcmServerCredentials;

	// configurable
	private int peerMapUpdateInterval;
	private int gcmSendRetries;

	/**
	 * Creates a TCP relay configuration
	 * 
	 * @return
	 */
	public static RelayConfig OpenTCP() {
		return new RelayConfig(RelayType.OPENTCP, 15, null, 0);
	}

	/**
	 * Creates an Android relay configuration
	 * 
	 * @param gcmServerCredentials to be able to communicate over Google Cloud Messaging
	 * @return
	 */
	public static RelayConfig Android(GCMServerCredentials gcmServerCredentials) {
		return new RelayConfig(RelayType.ANDROID, 60, gcmServerCredentials, 5);
	}

	private RelayConfig(RelayType type, int peerMapUpdateInterval, GCMServerCredentials gcmServerCredentials,
			int gcmSendRetries) {
		this.type = type;
		this.peerMapUpdateInterval = peerMapUpdateInterval;
		this.gcmServerCredentials = gcmServerCredentials;
		this.gcmSendRetries = gcmSendRetries;
	}

	/**
	 * @return the relay type
	 */
	public RelayType type() {
		return type;
	}

	/**
	 * Get the peer map update interval
	 */
	public int peerMapUpdateInterval() {
		return peerMapUpdateInterval;
	}

	/**
	 * Defines the time interval of sending the peer map of the unreachable peer
	 * to its relays. The routing requests are not relayed to the unreachable
	 * peer but handled by the relay peers. Therefore, the relay peers should
	 * always have an up-to-date peer map of the relayed peer
	 * 
	 * @param peerMapUpdateInterval the interval of updating the own peer map at the relay
	 * @return this instance
	 */
	public RelayConfig peerMapUpdateInterval(int peerMapUpdateInterval) {
		this.peerMapUpdateInterval = peerMapUpdateInterval;
		return this;
	}

	/**
	 * <strong>Only used for {@link RelayConfig#ANDROID}</strong><br>
	 * 
	 * @return the {@link GCMServerCredentials}. If this peer is an unreachable
	 *         Android device, these credentials need to be provided.
	 */
	public GCMServerCredentials gcmServerCredentials() {
		return gcmServerCredentials;
	}

	/**
	 * <strong>Only used for {@link RelayConfig#ANDROID}</strong><br>
	 * 
	 * @return the number of retires sending a GCM message
	 */
	public int gcmSendRetries() {
		return gcmSendRetries;
	}

	/**
	 * <strong>Only used for {@link RelayConfig#ANDROID}</strong><br>
	 * 
	 * @param gcmSendRetries the number of retries sending a GCM message
	 * @return this instance
	 */
	public RelayConfig gcmSendRetries(int gcmSendRetries) {
		this.gcmSendRetries = gcmSendRetries;
		return this;
	}
}
