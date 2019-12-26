import React, { Component } from 'react';
import styles from './index.css';
import { Input, Button, Alert, Tabs } from 'antd';
import { StickyContainer, Sticky } from 'react-sticky';
import QRCode from 'qrcode.react';
import ReactDOM from 'react-dom';


const { TextArea } = Input;
const { TabPane } = Tabs;

class IndexPage extends Component {
    state = {
        type: 'account',
        inVal: '杨春茂',
        qrcodeContent: '',
    }

    handleChange = e => {
      const val = e.target.value;
      if(val) {
        this.setState({
          inVal: e.target.value
        });
      }
    }

    
    handleClick = () => {
      const { inVal } = this.state;
      this.setState({
        type: 'inAccount',
        qrcodeContent: inVal,
      })
      console.log(inVal);

    }

    handleBackClick = () => {
      this.setState({
        type: 'account',
        inVal: '',
        qrcodeContent: '',
      });
    }

    save = () => {
      /** 保存canvast图片 */
      let image  = new Image();
      image.src =  this.qrcode.Image.toDataURL({format: 'image/png', quality:1 });
      var url = image.src.replace(/^data:image\/[^;]/, 'data:application/octet-stream');
      return image;
      window.open(url);
    }

    render() {
        const { inVal, type, qrcodeContent } = this.state;
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
                      {type == 'account'?
                      (
                        <TextArea rows={5} placeholder="请输入文字内容" size="large" onChange={this.handleChange} defaultValue={inVal} />
                      ) : (
                        <div>
                          <h2>二维码的内容</h2>
                          <Alert message={inVal} type="info"/>
                        </div>
                      )}
                    </div>
                    <div className={styles.contentButton}>
                      {type == 'account' ?
                      (
                        <Button type="primary" size="large" onClick={this.handleClick}>
                          生成二维码
                        </Button>
                      ) : (
                        <Button type="default" size="large" onClick={this.handleBackClick}>
                          再建一个
                        </Button>
                      )}
                    </div>
                </div>
            </div>
            <div id= "show" className={styles.show}>
              <div className={styles.showDetail}>
                  <div className={styles.imageDetail}>
                    {
                      qrcodeContent != '' ?
                      (
                        <QRCode value= {qrcodeContent} size={220} ref={ref=>(this.qrcode = ref)} />
                      ) : (
                        <span>此处生成二维码</span>
                      )
                    }                      
                  </div>
                  <div className={styles.showButton}>
                      <Button type="primary" block onClick={this.save}>
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

}

export default IndexPage;