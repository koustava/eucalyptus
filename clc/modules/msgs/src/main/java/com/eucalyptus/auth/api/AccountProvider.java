/*************************************************************************
 * Copyright 2009-2012 Eucalyptus Systems, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 * Please contact Eucalyptus Systems, Inc., 6755 Hollister Ave., Goleta
 * CA 93117, USA or visit http://www.eucalyptus.com/licenses/ if you need
 * additional information or have any questions.
 *
 * This file may incorporate work covered under the following copyright
 * and permission notice:
 *
 *   Software License Agreement (BSD License)
 *
 *   Copyright (c) 2008, Regents of the University of California
 *   All rights reserved.
 *
 *   Redistribution and use of this software in source and binary forms,
 *   with or without modification, are permitted provided that the
 *   following conditions are met:
 *
 *     Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *     Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer
 *     in the documentation and/or other materials provided with the
 *     distribution.
 *
 *   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *   "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *   LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 *   FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *   COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *   INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 *   BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *   LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *   CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 *   LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 *   ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *   POSSIBILITY OF SUCH DAMAGE. USERS OF THIS SOFTWARE ACKNOWLEDGE
 *   THE POSSIBLE PRESENCE OF OTHER OPEN SOURCE LICENSED MATERIAL,
 *   COPYRIGHTED MATERIAL OR PATENTED MATERIAL IN THIS SOFTWARE,
 *   AND IF ANY SUCH MATERIAL IS DISCOVERED THE PARTY DISCOVERING
 *   IT MAY INFORM DR. RICH WOLSKI AT THE UNIVERSITY OF CALIFORNIA,
 *   SANTA BARBARA WHO WILL THEN ASCERTAIN THE MOST APPROPRIATE REMEDY,
 *   WHICH IN THE REGENTS' DISCRETION MAY INCLUDE, WITHOUT LIMITATION,
 *   REPLACEMENT OF THE CODE SO IDENTIFIED, LICENSING OF THE CODE SO
 *   IDENTIFIED, OR WITHDRAWAL OF THE CODE CAPABILITY TO THE EXTENT
 *   NEEDED TO COMPLY WITH ANY SUCH LICENSES OR RIGHTS.
 ************************************************************************/

package com.eucalyptus.auth.api;

import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.eucalyptus.auth.AuthException;
import com.eucalyptus.auth.principal.AccessKey;
import com.eucalyptus.auth.principal.Account;
import com.eucalyptus.auth.principal.Certificate;
import com.eucalyptus.auth.principal.Group;
import com.eucalyptus.auth.principal.Policy;
import com.eucalyptus.auth.principal.Role;
import com.eucalyptus.auth.principal.User;

public interface AccountProvider {

  Account lookupAccountByName( String accountName ) throws AuthException;
  Account lookupAccountById( String accountId ) throws AuthException;
  Account lookupAccountByCanonicalId(String canonicalId) throws AuthException;

  Account addAccount( String accountName ) throws AuthException;
  void deleteAccount( String accountName, boolean forceDeleteSystem, boolean recursive ) throws AuthException;
  int countAccounts( ) throws AuthException;
  int countUsers( ) throws AuthException;
  int countGroups( ) throws AuthException;
  List<Account> listAllAccounts( ) throws AuthException;
  List<Account> listAccountsByStatus( User.RegistrationStatus status ) throws AuthException;
  Set<String> resolveAccountNumbersForName( String accountNameLike ) throws AuthException;
  
  List<User> listAllUsers( ) throws AuthException;

  boolean shareSameAccount( String userId1, String userId2 );
  
  User lookupUserById( String userId ) throws AuthException;
  User lookupUserByAccessKeyId( String keyId ) throws AuthException;
  User lookupUserByCertificate( X509Certificate cert ) throws AuthException;
  User lookupUserByConfirmationCode( String code ) throws AuthException;
  User lookupUserByEmailAddress( String email ) throws AuthException;

  /**
   * List users for the specified accounts.
   *
   * If the given collection is empty then users for all accounts are returned.
   */
  List<User> listUsersForAccounts( Collection<String> accountIds, boolean eager ) throws AuthException;

  /**
   * List of policies by user id for the specified users.
   *
   * If the given collection is empty then policies for all users are returned.
   */
  Map<String,List<Policy>> listPoliciesForUsers( Collection<String> userIds ) throws AuthException;

  /**
   * List of policies by group id for the specified groups.
   *
   * If the given collection is empty then policies for all groups are returned.
   */
  Map<String,List<Policy>> listPoliciesForGroups( Collection<String> groupId ) throws AuthException;

  /**
   * List of signing certificates by user id for the specified users.
   *
   * If the given collection is empty then certificates for all users are returned.
   */
  Map<String,List<Certificate>> listSigningCertificatesForUsers( Collection<String> userIds ) throws AuthException;

  /**
   * List of access keys by user id for the specified users.
   *
   * If the given collection is empty then keys for all users are returned.
   */
  Map<String,List<AccessKey>> listAccessKeysForUsers( Collection<String> userIds ) throws AuthException;

  Group lookupGroupById( String groupId ) throws AuthException;
  List<Group> listGroupsForAccounts( Collection<String> accountIds ) throws AuthException;

  Role lookupRoleById( String roleId ) throws AuthException;

  Certificate lookupCertificate( X509Certificate cert ) throws AuthException;
  
  AccessKey lookupAccessKeyById( String keyId ) throws AuthException;
  @Deprecated
  User lookupUserByName( String userName ) throws AuthException;
}
