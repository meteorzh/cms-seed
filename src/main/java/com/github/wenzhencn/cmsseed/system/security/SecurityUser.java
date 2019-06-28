package com.github.wenzhencn.cmsseed.system.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

import lombok.Getter;

/**
 * 自定义 UserDetails 对象
 * @author zhen.wen
 *
 */
@Getter
public class SecurityUser implements EntryUserDetails, CredentialsContainer {
	
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
	private final Long userId;
	private String password;
	private final String username;
	private final Set<GrantedAuthority> authorities;
	private final Set<String> entrys;
	private final boolean accountNonExpired;
	private final boolean accountNonLocked;
	private final boolean credentialsNonExpired;
	private final boolean enabled;

	public SecurityUser(Long userId, String username, String password, Collection<? extends GrantedAuthority> authorities, Set<String> entrys) {
		this(userId, username, password, true, true, true, true, authorities, entrys);
	}

	public SecurityUser(Long userId, String username, String password, boolean enabled, boolean accountNonExpired, 
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Set<String> entrys) {

		if (((username == null) || "".equals(username)) || (password == null)) {
			throw new IllegalArgumentException(
					"Cannot pass null or empty values to constructor");
		}

		this.userId = userId;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
		this.entrys = entrys;
	}
	
	public void eraseCredentials() {
		password = null;
	}
	
	private static SortedSet<GrantedAuthority> sortAuthorities(
			Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		// Ensure array iteration order is predictable (as per
		// UserDetails.getAuthorities() contract and SEC-717)
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(
				new AuthorityComparator());

		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority,
					"GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}

		return sortedAuthorities;
	}
	
	private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
		
		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			// Neither should ever be null as each entry is checked before adding it to
			// the set.
			// If the authority is null, it is a custom authority and should precede
			// others.
			if (g2.getAuthority() == null) {
				return -1;
			}
		
			if (g1.getAuthority() == null) {
				return 1;
			}
		
			return g1.getAuthority().compareTo(g2.getAuthority());
		}
	}

}
