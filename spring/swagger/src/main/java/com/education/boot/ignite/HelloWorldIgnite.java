package com.education.boot.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;

import java.util.Arrays;

public class HelloWorldIgnite {
    public static void main(String[] args) {
        System.out.println("Hello Ignite");
        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryMulticastIpFinder tcMp = new TcpDiscoveryMulticastIpFinder();
        tcMp.setAddresses(Arrays.asList("localhost"));
        spi.setIpFinder(tcMp);
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(false);
        cfg.setDiscoverySpi(spi);
        Ignite ignite = Ignition.start(cfg);
        IgniteCache<Integer, String> cache = ignite.getOrCreateCache("myCacheName");
        for (int i = 1; i <= 100; i++) {
            cache.put(i, Integer.toString(i));
        }

        for (int i = 1; i <= 100; i++) {
            System.out.println("Cache get:" + cache.get(i));
        }
    }
}
