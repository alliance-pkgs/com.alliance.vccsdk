var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'VCCSDK', 'coolMethod', [arg0]);
};

exports.launchVcc = function (vccHost, licenseKey, partnerKey, partnerProfileId, icNo, aomUserInfo, onboardingData, success, error) {
    exec(success, error, 'VCCSDK', 'launchVcc', [vccHost, licenseKey, partnerKey, partnerProfileId, icNo, aomUserInfo, onboardingData]);
};
