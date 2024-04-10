/********* VCCSDK.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>
@import VccLibrary;

@interface VCCSDK : CDVPlugin {
  // Member variables go here.
}

- (void)coolMethod:(CDVInvokedUrlCommand*)command;

- (void)launchVcc:(CDVInvokedUrlCommand*)command;
@end

@implementation VCCSDK

- (void)coolMethod:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* echo = [command.arguments objectAtIndex:0];

    if (echo != nil && [echo length] > 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:echo];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)launchVcc:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* vccHost = [command.arguments objectAtIndex:0];
    NSString* licenseKey = [command.arguments objectAtIndex:1];
    NSString* partnerKey = [command.arguments objectAtIndex:2];
    NSString* partnerProfileId = [command.arguments objectAtIndex:3];
    NSString* icNo = [command.arguments objectAtIndex:4];
    NSString* aomUserInfo = [command.arguments objectAtIndex:5];
    NSString* onboardingData = [command.arguments objectAtIndex:6];

    if ( (vccHost != nil && [vccHost length] > 0)
        && (licenseKey != nil && [licenseKey length] > 0)
        && (partnerKey != nil && [partnerKey length] > 0)
        && (partnerProfileId != nil && [partnerProfileId length] > 0)) {
        VCCViewController *vcc = [[VCCViewController alloc] init];
        [vcc setModalPresentationStyle: UIModalPresentationFullScreen];
        vcc.vccHost = vccHost;
        vcc.licenseKey = licenseKey;
        vcc.partnerKey = partnerKey;
        vcc.partnerProfileId = partnerProfileId;
        vcc.icNo = icNo;
        vcc.aomUserInfo = aomUserInfo;
        vcc.onboardingData = onboardingData;
        [self.viewController presentViewController:vcc animated:YES completion:nil];

        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"OK"];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
