// AUTOGENERATED FILE - DO NOT MODIFY!
// This file generated by Djinni from libunity.djinni

#import "DBQrcodeRecord.h"
#import "DBTransactionRecord.h"
#import "DBUriRecipient.h"
#import "DBUriRecord.h"
#import <Foundation/Foundation.h>
@protocol DBGuldenUnifiedFrontend;

/** Interface constants */
extern int32_t const DBGuldenUnifiedBackendVersion;

/** This interface will be implemented in C++ and can be called from any language. */
@interface DBGuldenUnifiedBackend : NSObject

/** Start the library */
+ (int32_t)InitUnityLib:(nonnull NSString *)dataDir
                signals:(nullable id<DBGuldenUnifiedFrontend>)signals;

/** Stop the library */
+ (void)TerminateUnityLib;

/** Generate a QR code for a string, QR code will be as close to widthHint as possible when applying simple scaling. */
+ (nonnull DBQrcodeRecord *)QRImageFromString:(nonnull NSString *)qrString
                                    widthHint:(int32_t)widthHint;

/** Get a receive address from the wallet */
+ (nonnull NSString *)GetReceiveAddress;

/** Get a receive address from the wallet */
+ (nonnull NSString *)GetRecoveryPhrase;

/** Check if text/address is something we are capable of sending money too */
+ (nonnull DBUriRecipient *)IsValidRecipient:(nonnull DBUriRecord *)request;

/** Attempt to pay a recipient */
+ (BOOL)performPaymentToRecipient:(nonnull DBUriRecipient *)request;

/** Get list of all transactions wallet has been involved in */
+ (nonnull NSArray<DBTransactionRecord *> *)getTransactionHistory;

@end