/*
 * Copyright 2015-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.poc.boot.rabbitmq.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poc.boot.rabbitmq.model.Order;
import com.poc.boot.rabbitmq.service.OrderMessageSender;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Spring controller which sends message as JMS.
 *
 * @author Raja Kolli
 *
 */
@Controller
@RequiredArgsConstructor
public class MessageController {

	private final OrderMessageSender orderMessageSender;

	@PostMapping("/sendMsg")
	public String handleMessage(@ModelAttribute Order order, RedirectAttributes redirectAttributes) {
		try {
			this.orderMessageSender.sendOrder(order);
			redirectAttributes.addFlashAttribute("message", "Order message sent successfully");
			return "redirect:/";
		}
		catch (JsonProcessingException exc) {
			// Spring 5 + way of throwing exception.
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable To Parse " + order.toString(),
					exc);
		}
	}

}
