import styles from './index.css';
import QRCode from 'qrcode.react';
import { Input, Button, Alert, Tabs } from 'antd';
import { StickyContainer, Sticky } from 'react-sticky';

const { TextArea } = Input;
const { TabPane } = Tabs;

export default function() {

  const renderTabBar = (props, DefaultTabBar) => (
    <Sticky bottomOffset={80}>
      {({ style }) => (
        <DefaultTabBar {...props} style={{ ...style, zIndex: 1, background: '#fff' }} />
      )}
    </Sticky>
  );

  return (
    <div className={styles.normal}>
        <div id= "content" className={styles.content}>
            <div className={styles.contentDetail}>
                <div className={styles.contentInput}>
                  <TextArea rows={5} placeholder="请输入文字内容" size="large" />
                  {/* <h2>二维码的内容</h2>
                  <Alert message="杨春茂" type="info"/> */}
                </div>
                <div className={styles.contentButton}>
                    <Button type="primary" size="large">
                      生成二维码
                    </Button>
                    {/* <Button type="default" size="large">
                      再建一个
                    </Button> */}
                </div>
            </div>
        </div>
        <div id= "show" className={styles.show}>
          <div className={styles.showDetail}>
              <div className={styles.imageDetail}>
                  <span>此处生成二维码</span>
              </div>
              <div className={styles.showButton}>
                  <Button type="primary" block>
                      保存图片
                  </Button>
              </div>
              <div className={styles.showTab}>
                <StickyContainer>
                    <Tabs defaultActiveKey="1" renderTabBar={renderTabBar}>
                        <TabPane tab="基本" key="1" style={{ height: 20 }}>
                          基本
                        </TabPane>
                        <TabPane tab="颜色" key="2">
                          颜色
                        </TabPane>
                        <TabPane tab="LOGO" key="3">
                          LOGO
                        </TabPane>
                        <TabPane tab="美化器" key="4">
                          美化器
                        </TabPane>
                    </Tabs>
                </StickyContainer>
              </div>
          </div>
        </div>
    </div>
  );
}
