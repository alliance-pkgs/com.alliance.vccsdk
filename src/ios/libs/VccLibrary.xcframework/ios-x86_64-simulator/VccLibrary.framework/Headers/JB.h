//
//  JB.m
//  isJailbroken
//
//  Created by Kc Lim on 19/10/2020.
//  Copyright © 2020 Kc Lim. All rights reserved.
//
#import <Foundation/Foundation.h>
#import <sys/stat.h>
#import <UIKit/UIKit.h>
#include <mach-o/dyld.h>
#include <assert.h>
#include <stdbool.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/sysctl.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <dlfcn.h>
#include "TargetConditionals.h"


NS_ASSUME_NONNULL_BEGIN

@interface JB : NSObject
//BOOL isJb();
+ (BOOL) isInjectedWithDynamicLibrary;
//BOOL isSecurityCheckPassed();
//BOOL isDebugged();
//BOOL isFromAppStore();
@end

NS_ASSUME_NONNULL_END
