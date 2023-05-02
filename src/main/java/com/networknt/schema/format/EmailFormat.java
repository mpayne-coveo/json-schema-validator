/*
 * Copyright (c) 2016 Network New Technologies Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.networknt.schema.format;

import com.networknt.org.apache.commons.validator.routines.EmailValidator;

public class EmailFormat extends AbstractFormat {

    private final EmailValidator emailValidator;

    public EmailFormat() {
        super("email", "must be a valid RFC 5321 Mailbox");
        this.emailValidator = new SpecialEmailValidator(true, true);
    }

    @Override
    public boolean matches(String value) {
        return this.emailValidator.isValid(value);
    }

    static class SpecialEmailValidator extends EmailValidator {
        private static final long serialVersionUID = 1L;

        public SpecialEmailValidator(boolean b, boolean c) {
            super(b, c);
        }

        @Override
        protected boolean isValidDomain(String domain) {
            return super.isValidDomain(domain.startsWith("[IPv6:") ? domain.replace("IPv6:", "") : domain);
        }

    }
}
