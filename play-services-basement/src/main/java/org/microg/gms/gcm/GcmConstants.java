/*
 * Copyright (C) 2013-2017 microG Project Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.microg.gms.gcm;

public final class GcmConstants {
    public static final String ACTION_C2DM_RECEIVE = "com.mgoogle.android.c2dm.intent.RECEIVE";
    public static final String ACTION_C2DM_REGISTER = "com.mgoogle.android.c2dm.intent.REGISTER";
    public static final String ACTION_C2DM_REGISTRATION = "com.mgoogle.android.c2dm.intent.REGISTRATION";
    public static final String ACTION_C2DM_UNREGISTER = "com.mgoogle.android.c2dm.intent.UNREGISTER";

    public static final String EXTRA_APP = "app";
    public static final String EXTRA_APP_OVERRIDE = "org.microg.gms.gcm.APP_OVERRIDE";
    public static final String EXTRA_APP_VERSION_NAME = "app_ver_name";
    public static final String EXTRA_COLLAPSE_KEY = "collapse_key";
    public static final String EXTRA_DELETE = "delete";
    public static final String EXTRA_ERROR = "error";
    public static final String EXTRA_FROM = "from";
    public static final String EXTRA_KID = "kid";
    public static final String EXTRA_MESSENGER = "google.messenger";
    public static final String EXTRA_MESSAGE_ID = "google.message_id";
    public static final String EXTRA_REGISTRATION_ID = "registration_id";
    public static final String EXTRA_RETRY_AFTER = "Retry-After";
    public static final String EXTRA_SENDER = "sender";
    public static final String EXTRA_SEND_TO = "google.to";
    public static final String EXTRA_SEND_FROM = "google.from";
    public static final String EXTRA_TTL = "google.ttl";
    public static final String EXTRA_UNREGISTERED = "unregistered";

    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
}
