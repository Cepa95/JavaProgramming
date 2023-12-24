package com.oss.jceprnic.task06.service;

import com.oss.jceprnic.task06.model.Device;
import com.oss.jceprnic.task06.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;


}
