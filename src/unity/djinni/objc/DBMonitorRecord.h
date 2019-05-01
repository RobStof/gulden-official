// AUTOGENERATED FILE - DO NOT MODIFY!
// This file generated by Djinni from libunity.djinni

#import <Foundation/Foundation.h>

/** monitoring stats */
@interface DBMonitorRecord : NSObject
- (nonnull instancetype)initWithPartialHeight:(int32_t)partialHeight
                                partialOffset:(int32_t)partialOffset
                                 prunedHeight:(int32_t)prunedHeight
                           processedSPVHeight:(int32_t)processedSPVHeight
                               probableHeight:(int32_t)probableHeight;
+ (nonnull instancetype)monitorRecordWithPartialHeight:(int32_t)partialHeight
                                         partialOffset:(int32_t)partialOffset
                                          prunedHeight:(int32_t)prunedHeight
                                    processedSPVHeight:(int32_t)processedSPVHeight
                                        probableHeight:(int32_t)probableHeight;

@property (nonatomic, readonly) int32_t partialHeight;

@property (nonatomic, readonly) int32_t partialOffset;

@property (nonatomic, readonly) int32_t prunedHeight;

@property (nonatomic, readonly) int32_t processedSPVHeight;

@property (nonatomic, readonly) int32_t probableHeight;

@end